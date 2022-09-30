package org.amex.model;

import java.util.ArrayList;



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
public class Root {
 
    public ArrayList<GPinEligibility> gPinEligibility;

}
