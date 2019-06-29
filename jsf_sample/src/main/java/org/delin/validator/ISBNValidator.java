package org.delin.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.RegexValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ISBNValidator")
public class ISBNValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        int[] factor = {1, 3};
        System.out.println(value);
        RegexValidator validator = new RegexValidator();
        validator.setPattern("ISBN[0-9]{3}-[0-9]-[0-9]{4}-[0-9]{4}-[0-9]");
        try {
            validator.validate(facesContext, uiComponent, value);
        } catch (ValidatorException ve) {
            throw new ValidatorException(new FacesMessage("isbn is invalid"));
        }
        String isbn = value.toString().substring("ISBN".length()).trim().replace("-", "");
        System.out.println(isbn);
        int sum = 0;
        for (int i = 0; i < 12; ++i) {
            sum += (isbn.charAt(i) - '0') * factor[i % 2];
        }
        int mod = sum % 10;
        int checkSum = mod == 0 ? 0 : 10 - mod;
        if (checkSum != isbn.charAt(12) - '0') {
            throw new ValidatorException(new FacesMessage("isbn is invalid"));
        }
    }
}
