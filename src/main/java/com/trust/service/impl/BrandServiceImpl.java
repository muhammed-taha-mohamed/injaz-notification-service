package com.trust.service.impl;

import com.trust.config.security.JwtTokenProvider;
import com.trust.dao.BrandRepository;
import com.trust.dto.GenerateTokenDto;
import com.trust.dto.LoginDTO;
import com.trust.dto.LoginResponseDTO;
import com.trust.dto.brand.BrandDTO;
import com.trust.dto.brand.CreateBrandDTO;
import com.trust.enummeration.Role;
import com.trust.error.exceptions.AbstractTrustException;
import com.trust.mapper.BrandMapper;
import com.trust.model.Brand;
import com.trust.model.User;
import com.trust.service.IBrandService;
import com.trust.util.ErrorMessagesUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.trust.util.UserKeyUtil.generateBrandKey;

@Service
@Slf4j
@AllArgsConstructor
public class BrandServiceImpl implements IBrandService {

    private final BrandRepository brandRepository;
    private final UserServiceImpl userService;
    private final BrandMapper brandMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final ErrorMessagesUtil errorMessagesUtil;


    /**
     * Creates a new brand based on the provided DTO.
     * @param dto The DTO containing the brand information.
     * @return The created brand DTO.
     * @throws AbstractTrustException If an error occurs while creating the brand.
     */
    @Override
    public BrandDTO create(CreateBrandDTO dto) {
        try {
            Brand brand = brandMapper.toEntity(dto);

            // Create a user for the login process
            User user = userService.create(User.builder()
                    .email(dto.getEmail())
                    .role(Role.BRAND_ADMIN)
                    .password(dto.getPassword())
                    .phoneNumber(dto.getPhoneNumber())
                    .name(brand.getName())
                    .active(true)
                    .image(brand.getImage())
                    .build());

            brand.setBrandKey(generateBrandKey());
            brand.setUsers(List.of(user));
            return brandMapper.toDto(brandRepository.save(brand));
        } catch (Exception e) {
            log.error("An error occurred while creating a brand: {}", e.getMessage());
            throw new AbstractTrustException(e.getMessage());
        }
    }

    /**
     * Performs a login operation with the provided login DTO.
     * @param loginDTO The login DTO containing the email and password.
     * @return The login response DTO if the login is successful.
     * @throws AbstractTrustException if the login credentials are not valid or an error occurs during the login process.
     */
    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
        try {
            Optional<User> userOptional = userService.getByEmailAndPassword(loginDTO);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Optional<Brand> brandOptional = brandRepository.findByUsers_id(user.getId());
                if (brandOptional.isPresent()) {
                    Brand brand = brandOptional.get();
                    String token = jwtTokenProvider.generateToken(GenerateTokenDto.builder()
                            .userId(user.getId())
                            .username(user.getName())
                            .userKey(brand.getBrandKey())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .build());

                    return LoginResponseDTO.builder()
                            .image(user.getImage())
                            .id(brand.getId())
                            .image(user.getImage())
                            .name(user.getName())
                            .token(token)
                            .email(user.getEmail())
                            .build();
                }
            }
            throw new IllegalArgumentException(errorMessagesUtil.getErrorMessage("email.password.not.valid"));
        } catch (Exception e) {
            log.error("An error occurred during login: {}", e.getMessage());
            throw new AbstractTrustException(e.getMessage());
        }
    }


    /**
     * Finds a brand by its ID.
     * @param id The ID of the brand to find.
     * @return The brand DTO if found.
     * @throws AbstractTrustException if the brand is not found or an error occurs during the search.
     */
    @Override
    public BrandDTO findById(String id) {
        try {
            return brandMapper.toDto(brandRepository.findById(id)
                    .orElseThrow(() -> new AbstractTrustException("Brand not found")));
        } catch (Exception e) {
            log.error("An error occurred while finding brand with ID {}: {}", id, e.getMessage());
            throw new AbstractTrustException(e.getMessage());
        }
    }
}
