package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.ReviewDAO;
import ru.icoltd.rvs.dtos.ReviewDto;
import ru.icoltd.rvs.entity.Review;
import ru.icoltd.rvs.mappers.ReviewMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO dao;

    private final ReviewMapper mapper;

    @Override
    @Transactional
    public Review save(Review review) {
        return dao.makePersistent(review);
    }

    @Override
    public List<ReviewDto> findAllByRestaurantId(Long restaurantId) {
        return StreamSupport.stream(dao.findAllByRestaurantId(restaurantId).spliterator(), false)
                .map(mapper::fromReview)
                .collect(Collectors.toList());
    }
}
