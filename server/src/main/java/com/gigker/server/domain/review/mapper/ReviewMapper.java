package com.gigker.server.domain.review.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.gigker.server.domain.content.mapper.ContentApplyMapper;
import com.gigker.server.domain.member.mapper.MemberMapper;
import com.gigker.server.domain.review.dto.ReviewDto;
import com.gigker.server.domain.review.entity.Review;

@Mapper(componentModel = "spring",
	uses = {MemberMapper.class, ContentApplyMapper.class},
	unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
	// Dto.writerId <=> Review.contentApply.contentApplyId 매핑
	// Dto.recipientId <=> Review.member.memberId 매핑

	@Mapping(source = "writerId", target = "contentApply.contentApplyId")
	@Mapping(source = "recipientId", target = "member.memberId")
	Review postToReview(ReviewDto.Post post);

	@Mapping(source = "contentApply.contentApplyId", target = "writerId")
	@Mapping(source = "member.memberId", target = "recipientId")
	ReviewDto.Response reviewToResponse(Review review);

	@Mapping(source = "contentApply.contentApplyId", target = "writerId")
	@Mapping(source = "member.memberId", target = "recipientId")
	List<ReviewDto.Response> reviewsToResponses(List<Review> reviews);
}