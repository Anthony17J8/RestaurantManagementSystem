package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.ReviewDAO;
import ru.icoltd.rvs.entity.Review;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewDAO dao;

    @Autowired
    public ReviewServiceImpl(ReviewDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Review> findAllByRestaurantId(int restaurantId) {
        return dao.findAllByRestaurantId(restaurantId);
    }
}
