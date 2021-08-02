package org.guzman.despachalo.commons.errors;

/**
 * User input exception should be used when the user has given
 * wrong inputs that break the business rules and cannot execute
 * the service correctly.
**/
public interface UserInputException extends BusinessException {
}
