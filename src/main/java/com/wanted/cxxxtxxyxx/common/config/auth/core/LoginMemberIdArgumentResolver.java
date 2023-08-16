package com.wanted.cxxxtxxyxx.common.config.auth.core;

import com.wanted.cxxxtxxyxx.common.config.auth.annotation.LoginMemberId;
import com.wanted.cxxxtxxyxx.common.error.exception.NotExistLoginInformationException;
import com.wanted.cxxxtxxyxx.common.response.code.CommonErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginMemberIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMemberId.class) && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NotExistLoginInformationException(CommonErrorCode.HANDLE_UNAUTHORIZED);
        }
        System.out.println(authentication.getName());
        return Long.parseLong(authentication.getName());
    }
}
