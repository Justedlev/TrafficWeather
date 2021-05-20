package org.traffic.weather.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeWithReturnedData<D> {

    Codes code;
    D returnedData;

}
