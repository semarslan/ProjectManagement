package com.semarslan.projectmanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectIdExceptionResponse {

    private String projectIdentifier;
}
