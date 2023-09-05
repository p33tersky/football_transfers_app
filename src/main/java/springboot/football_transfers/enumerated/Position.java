package springboot.football_transfers.enumerated;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum Position {

    GK("goalkeeper", "GK"),
    CB("center back", "CB"),
    RB("right back", "RB"),
    LB("left back", "LB"),
    RWB("right wing back", "RWB"),
    LWB("left wing back", "LWB"),
    CDM("central defensive midfielder", "CDM"),
    RM("right midfielder", "RM"),
    CM("central midfielder", "CM"),
    LM("left midfielder", "LM"),
    RW("right winger", "RW"),
    LW("left winger", "LW"),
    CAM("central attacking midfielder", "CAM"),
    CF("center forward", "CF"),
    ST("striker", "ST");


    private String positionName;
    private String shortTermOfPositionName;
}
