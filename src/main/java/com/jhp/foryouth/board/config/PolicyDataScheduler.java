package com.jhp.foryouth.board.config;

import com.jhp.foryouth.board.dto.PolicyApiResponseDto;
import com.jhp.foryouth.board.dto.PolicyDto;
import com.jhp.foryouth.board.entity.CrawledPost;
import com.jhp.foryouth.board.repository.CrawledPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PolicyDataScheduler {

    private final RestTemplate restTemplate;
    private final CrawledPostRepository crawledPostRepository;

    @Scheduled(cron = "0 0 4 * * *")
    public void fetchYouthPolicies() {
        System.out.println("청년 정책 데이터 수집을 시작합니다...");

        String endpointUrl = "";
        String apiKey = "";

        URI uri = UriComponentsBuilder
                .fromUriString(endpointUrl)
                .queryParam("openApiVlak", apiKey)
                .queryParam("display", 100)
                .queryParam("pageIndex", 1)
                .encode()
                .build()
                .toUri();

        PolicyApiResponseDto response = restTemplate.getForObject(uri, PolicyApiResponseDto.class);
        if(response != null && response.getPolicyList() != null) {
            List<PolicyDto> policies = response.getPolicyList();

            for(PolicyDto policy : policies) {
                if(!crawledPostRepository.existsByUrl(policy.getPolicyId())) {
                    CrawledPost newPost = CrawledPost.builder()
                            .title(policy.getTitle())
                            .content(policy.getContent())
                            .url(policy.getPolicyId())
                            .source("온라인청년센터-API")
                            .build();

                    crawledPostRepository.save(newPost);
                    System.out.println("새로운 데이터 저장 : " + policy.getTitle());
                }
            }
        }
        System.out.println("청년 정책 데이터 수집을 종료합니다.");
    }
}
