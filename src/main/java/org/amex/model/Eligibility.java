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
public class Eligibility{

	public boolean gPinInq;
    public boolean gPinView;
    public boolean gPinSelfSelect;
    public boolean gPinReminder;
    public boolean gPinUnlockInitiate;
    public boolean gPinPips;
    public boolean gPinPipsReset;
    public boolean gPinChange;
    public boolean gPinReset;
    public boolean gPinUnlock;
    public boolean gPinValidate;
    public boolean getGlobalPin;
    public boolean lockPin;
}