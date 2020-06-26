package com.hobbie.services.user.usecase;

import com.hobbie.services.user.dataprovider.UserDataProvider;
import com.hobbie.services.user.entrypoint.dto.FeedbackDto;
import com.hobbie.services.user.usecase.model.Feedback;
import com.hobbie.services.user.usecase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUseCase {

    @Autowired
    private UserDataProvider userDataProvider;

    public void addFeedback(User user, FeedbackDto dto) {
        Feedback feedback = user.getFeedback();

        if (feedback == null) {

            List<Double> notas = new ArrayList<>();

            notas.add(dto.getNota());

            Feedback fdbc = Feedback.builder()
                    .nota(notas)
                    .mediaNota(notas.stream().mapToDouble(a -> a).average().orElse(5.00))
                    .avaliacoes(1)
                    .build();

            user.setFeedback(fdbc);

        } else {
            feedback.getNota().add(dto.getNota());
            feedback.setMediaNota(feedback.getMediaNota());
            feedback.setAvaliacoes(feedback.getAvaliacoes() + 1);
            user.setFeedback(feedback);
        }
        userDataProvider.addUser(user);
    }
}
