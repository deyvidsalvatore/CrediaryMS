package com.deyvidsalvatore.crediary.mscreditappraiser.domain.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProtocolCardRequest implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private String protocol;
}
