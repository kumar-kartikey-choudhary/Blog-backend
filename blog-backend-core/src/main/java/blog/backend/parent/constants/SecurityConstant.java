package blog.backend.parent.constants;

import lombok.Data;

@Data
public class SecurityConstant {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SECRET_KEY = "yourSecretKey";
    public static final long EXPIRATION_TIME = 864_000_00;
}
