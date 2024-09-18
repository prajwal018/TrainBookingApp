package com.prajwal.TrainBookingApp.configuration.filter;

import com.prajwal.TrainBookingApp.model.Users;
import com.prajwal.TrainBookingApp.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {


    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        if (request.getRequestURI().startsWith("/admin")) {
            String apiKeyHeader = request.getHeader("x-api-key");
//            System.out.println(apiKeyHeader);

            if (apiKeyHeader == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Missing API key");
                return;
            }

//            Optional<Users> adminUser = userRepository.findByRole(Role.ADMIN)
//                    .stream()
//                    .filter(user -> user.getApiKey() != null)  // Filter out users with null API keys
//                    .filter(user -> {
//                        return ApiKeyUtil.matches(apiKeyHeader, user.getApiKey());  // Compare API key hashes
//                    })
//                    .findFirst();

            // Fetch admin users and check the API key
            Optional<Users> adminUser = userRepository.findByRoleAndApiKey("ADMIN", apiKeyHeader);
//            System.out.println(adminUser.get().getApiKey());

            if (!adminUser.isPresent()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid API key");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
