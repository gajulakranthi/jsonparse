package org.amex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class GPinEligibilityForSource{
    public String sourceCode;
    public Eligibility eligibility;
}