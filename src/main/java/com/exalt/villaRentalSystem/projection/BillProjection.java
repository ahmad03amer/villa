package com.exalt.villaRentalSystem.projection;

import java.util.Date;

public interface BillProjection {
    int getId();
    Date getDate();
    double getAmount();
}
