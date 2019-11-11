package ru.icoltd.rvs.validation;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.service.UserService;
import ru.icoltd.rvs.util.MockDataUtils;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailValidatorTest {

    @InjectMocks
    private EmailValidator validator;

    @Mock
    private UserService userService;

    private ConstraintValidatorContext mockContext;

    @BeforeEach
    void setUp() {
        mockContext = Mockito.mock(ConstraintValidatorContext.class);
    }

    @Test
    void testIsValidPattern() {
        assertFalse(validator.isValid("_bugs@ycom", mockContext));
        assertFalse(validator.isValid(".dx../.,", mockContext));
        assertTrue(validator.isValid("jam@gmail.com", mockContext));
        assertTrue(validator.isValid("pat_black@yahoo.com", mockContext));
        assertFalse(validator.isValid("inval@gmail._com", mockContext));
        assertFalse(validator.isValid("yandex.ru", mockContext));
        assertTrue(validator.isValid("221@mi.ru", mockContext));
        assertTrue(validator.isValid("i.am.jake@mi.ru", mockContext));
    }

    @Test
    void testIsValidExistEmail() {
        when(userService.findUserByEmail(anyString())).thenReturn(MockDataUtils.getMockUser());
        assertFalse(validator.isValid("existEmail@gr.edu", mockContext));
    }

    @Test
    void testIsValidEmptyOrNullEmail() {
        // these cases are handled by @NotNull and @Size
        assertTrue(validator.isValid(StringUtils.EMPTY, mockContext));
        assertTrue(validator.isValid(null, mockContext));
    }
}