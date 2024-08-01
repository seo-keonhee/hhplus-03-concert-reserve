package io.hhp.concertreserve.token.interfaces;

import io.hhp.concertreserve.token.domain.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 대기열에 추가하거나 활성화 토큰을 발급합니다.
     */
    @PostMapping("/token")
    public ResponseEntity<TokenDto> getToken(@RequestParam(name = "userId") String userId) {
        // 토큰을 발급하고 TokenDto를 반환합니다.
        return ResponseEntity.status(HttpStatus.OK)
                .body(TokenDto.toTokenDto(tokenService.getToken(userId)));
    }
}
