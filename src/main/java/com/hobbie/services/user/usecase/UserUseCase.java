package com.hobbie.services.user.usecase;

import com.hobbie.services.user.entrypoint.dto.FeedbackDto;
import com.hobbie.services.user.usecase.model.Feedback;
import com.hobbie.services.user.usecase.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserUseCase {

    public void addFeedback(User user, FeedbackDto dto) {
        Feedback feedback = user.getFeedback();

        if(feedback == null) {
            Feedback fdbc = Feedback.builder()
                    .avaliacoes(1)
                    .nota(Double.valueOf(dto.getNota()))
                    .build();
            user.setFeedback(fdbc);
        }
        else {
            feedback.setAvaliacoes(feedback.getAvaliacoes() + 1);
            feedback.setNota(null);
        }
    }
}
