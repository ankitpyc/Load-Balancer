package com.loadbalancer.amble.ServerConfiguration.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveNodeList {
    private ActiveNode start, end;
}
