package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.ReviewDAO;
import ru.icoltd.rvs.dtos.ReviewDto;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.mappers.ReviewMapper;
import ru.icoltd.rvs.mappers.UserMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO dao;

    private final ReviewMapper reviewMapper;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public ReviewDto save(ReviewDto review, User currentUser) {
        review.setUser(userMapper.fromUser(currentUser));
        return reviewMapper.fromReview(dao.makePersistent(reviewMapper.toReview(review)));
    }

    @Override
    public List<ReviewDto> findAllByRestaurantId(Long restaurantId) {
        return StreamSupport.stream(dao.findAllByRestaurantId(restaurantId).spliterator(), false)
                .map(reviewMapper::fromReview)
                .collect(Collectors.toList());
    }
}
