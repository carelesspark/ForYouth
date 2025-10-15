package com.jhp.foryouth.board.config;

import com.jhp.foryouth.board.dto.ContentApiResponseDto;
import com.jhp.foryouth.board.dto.ContentDto;
import com.jhp.foryouth.board.dto.PolicyApiResponseDto;
import com.jhp.foryouth.board.dto.PolicyDto;
import com.jhp.foryouth.board.entity.CrawledPost;
import com.jhp.foryouth.board.repository.CrawledPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class YouthCenterDataScheduler {

    private final RestTemplate restTemplate;
    private final CrawledPostRepository crawledPostRepository;

    @Value("${youth-center.policy.api.key}")
    private String policyApiKey;

    @Value("${youth-center.policy.api.endpoint-url}")
    private String policyEndpointUrl;

    @Value("${youth-center.content.api.key}")
    private String contentApiKey;

    @Value("${youth-center.content.api.endpoint-url}")
    private String contentEndpointUrl;

//    @Scheduled(cron = "0 0 4 * * *")
    public void fetchYouthPolicies() {
        System.out.println("청년 정책 데이터 수집을 시작합니다...");

        URI uri = UriComponentsBuilder
                .fromUriString(policyEndpointUrl)
                .queryParam("apiKeyNm", policyApiKey)
                .queryParam("pageSize", 100)
                .queryParam("pageNum", 1)
                .encode()
                .build()
                .toUri();

        try {
            PolicyApiResponseDto response = restTemplate.getForObject(uri, PolicyApiResponseDto.class);
            if(response != null && response.getPolicyList() != null) {
                for(PolicyDto policy : response.getPolicyList()) {
                    if(!crawledPostRepository.existsByUrl(policy.getPolicyId())) {
                        CrawledPost newPost = CrawledPost.builder()
                                .title(policy.getTitle())
                                .content(policy.getContent())
                                .url(policy.getPolicyId())
                                .source("온통청년-청년정책API")
                                .build();

                        crawledPostRepository.save(newPost);
                        System.out.println("새로운 데이터 저장 : " + policy.getTitle());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("정책 API 호출 중 오류 발생 : " + e.getMessage());
        }
        System.out.println("청년 정책 데이터 수집을 종료합니다.");
    }

//    @Scheduled(cron = "0 5 4 * * *")
    public void fetchYouthContents() {
        System.out.println("청년 콘텐츠 데이터 수집을 시작합니다...");

        URI uri = UriComponentsBuilder
                .fromUriString(contentEndpointUrl)
                .queryParam("apiKeyNm", contentApiKey)
                .queryParam("pageSize", 100)
                .queryParam("pageNum", 1)
                .encode()
                .build()
                .toUri();

        try {
            ContentApiResponseDto response = restTemplate.getForObject(uri, ContentApiResponseDto.class);

            if(response != null && response.getContentList() != null) {
                for(ContentDto content : response.getContentList()) {

                    if(!crawledPostRepository.existsByUrl(content.getContentId())) {
                        CrawledPost newPost = CrawledPost.builder()
                                .title(content.getTitle())
                                .content(content.getContent())
                                .url(content.getUrl())
                                .source("온통청년-콘텐츠API")
                                .build();

                        crawledPostRepository.save(newPost);
                        System.out.println("새로운 데이터 저장 : " + content.getTitle());
                    }
                }
            }
        } catch(Exception e) {
            System.out.println("콘텐츠 API 호출 중 오류 발생 : " + e.getMessage());
        }
        System.out.println("청년 콘텐츠 데이터 수집을 종료합니다.");
    }
}
