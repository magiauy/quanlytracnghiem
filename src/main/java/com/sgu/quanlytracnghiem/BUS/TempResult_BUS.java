package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Result_DAO;
import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Exam;
import com.sgu.quanlytracnghiem.Interface.BUS.IResult;
import com.sgu.quanlytracnghiem.DTO.Result;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TempResult_BUS implements IResult {

    GenericDAO<Result> result_dao;
    @Setter
    Result result = new Result();

    public TempResult_BUS() {
        result_dao = new Result_DAO();
    }

    @Override
    @Async
    public void updateResult( int index, Answers answer) {
        result.getAnswers().set(index, answer);
        System.out.println(result.getAnswers());
        result.setResultScore(calMark());
        result_dao.update(result);
    }

    private BigDecimal calMark(){
        BigDecimal mark = new BigDecimal(0);
        boolean isAllCorrect = true;
        //Điểm sẽ chia đều cho số câu hỏi
        for (Answers answer : result.getAnswers()) {
            if (answer!=null){
                if (answer.isAnswerCorrect()) {
                    System.out.println(new BigDecimal(result.getAnswers().size()));
                    System.out.println(new BigDecimal(10).divide(new BigDecimal(result.getAnswers().size()), RoundingMode.HALF_UP));
                    mark = mark.add(new BigDecimal(10).divide(new BigDecimal(result.getAnswers().size()),2, RoundingMode.HALF_UP));
                }else {
                    isAllCorrect = false;
                }
            }
        }
        if (isAllCorrect){
            mark = new BigDecimal(10);
        }
        return mark;
    }

    @Override
    @Async
    public void add(Result result) {
        result_dao.insert(result);
        this.result = result;
    }

    @Override
    public void  submitResult(Result result) {
        Result dbResult = result_dao.getById(String.valueOf(result.getResultID()));
        if (dbResult != null) {
            result_dao.update(result);
            System.out.println("Submit result");
        } else {
            result_dao.insert(result);
        }
    }

}
