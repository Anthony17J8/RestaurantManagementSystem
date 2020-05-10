package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.ReviewDAO;
import ru.icoltd.rvs.entity.Review;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO dao;

    @Override
    @Transactional
    public Review save(Review review) {
        return dao.makePersistent(review);
    }
}
