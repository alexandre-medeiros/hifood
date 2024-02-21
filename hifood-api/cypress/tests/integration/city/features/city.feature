Feature: City endpoint tests

  Scenario: GET ALL: GET to /cities should return all Citys
    Given I hit "GET" to endpoint "/cities"
    Then should return all "city"
    And should return status code "200"

  Scenario: GET ONE: GET to /cities/1 should return City existent with id=1 in database
    Given I hit "GET" to endpoint <url>
    Then should return the "city" existent in database with same id <id>
    And should return status code "200"

    Examples:
      | url         | id  |
      | '/cities/1' | '1' |

  Scenario: NOT FOUND: GET to /cities/999 should return no City found
    Given I hit "GET" to endpoint <url>
    Then there is no "city" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No City entity with id 999 exists!"

    Examples:
      | url           | id    |
      | '/cities/999' | '999' |

  Scenario: CREATE: POST to /cities should create a new city
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": "New City",
        "state": {
          "id": 1
        }
      }
      """
    Then the "city" is registred with success
    And should return status code "201"

  Scenario: CREATE FAIL: POST to /cities fail because state does not exist
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": "Name of city create fail",
        "state": {
          "id": 999
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "No State entity with id 999 exists!"

  Scenario: CREATE FAIL: POST to /cities fail because field name is too small
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": "Ve",
        "state": {
          "id": 1
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 80"

  Scenario: CREATE FAIL: POST to /cities fail because field name is too large
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": "Very much big great gigant value Very much big great gigant value Very much big great",
        "state": {
          "id": 1
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 80"

  Scenario: CREATE FAIL: POST to /cities fail because field name must not be null
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": null,
        "state": {
          "id": 1
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 80"

  Scenario: CREATE FAIL: POST to /cities fail because field name must exist
    Given I hit "POST" to "/cities" with data:
      """
      {
        "state": {
          "id": 1
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 80"

  Scenario: CREATE FAIL: POST to /cities fail because field state must exist
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": "Name of city"
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "state" to field "Field state  must not be null"

  Scenario: CREATE FAIL: POST to /cities fail because field state id must not be null
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": "Name of city",
        "state": {
          "id": null
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "state.id" to field "Field state.id  must not be null"

  Scenario: CREATE FAIL: POST to /cities fail because state id has invalid type
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": "Name of city create fail",
        "state": {
          "id": "AA"
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Invalid Body"
    And should return error status at body "400"
    And should return error detail message "The property 'state.id' received the value 'AA' that is invalid. Send a compatible value with 'Long'"

  Scenario: CREATE FAIL: POST to /cities fail because an unrecognized field exist
    Given I hit "POST" to "/cities" with data:
      """
      {
        "description": "description",
        "name": "Name of city create fail",
        "state": {
          "id": 1
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Invalid Body"
    And should return error status at body "400"
    And should return error detail message "Unrecognized field 'description' "

  Scenario: UPDATE: PUT to /cities/1 must update the City with id=1
    Given I hit "PUT" to "/cities/1" with data:
      """
      {
        "name": "Uberlândia Name Updated",
        "state": {
          "id": 1
        }
      }
      """
    Then the "city" with id "1" is updated with success
    And should return status code "200"

  Scenario: UPDATE FAIL: PUT to /cities/999 must return not found City
    Given I hit "PUT" to <url> with data:
      """
      {
        "name": "Name Unexistent City",
        "state": {
          "id": 1
        }
      }
      """
    Then there is no "city" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No City entity with id 999 exists!"

    Examples:
      | url           | id    |
      | '/cities/999' | '999' |

  Scenario: UPDATE FAIL: PUT to /cities/1 with unexistent state id must return not found State
    Given there is a "city" with id <id>
    When I hit "PUT" to <url> with data:
      """
      {
        "name": "New Updated Expected",
        "state": {
          "id": 999
        }
      }
      """
    Then the "city" with id <id> was not updated
    And should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "No State entity with id 999 exists!"

    Examples:
      | url         | id  |
      | '/cities/1' | '1' |

  Scenario: REMOVE: DELETE to /cities/{id} should remove deleteable City in database
    Given I hit "POST" to "/cities" with data:
      """
      {
        "name": "Deleteable City",
        "state": {
          "id": 1
        }
      }
      """
    Then the "city" is registred with success
    Given I hit DELETE to endpoint <url> with a deleteable id
    Then should remove the "city" existent in database with same id
    And should return status code "204"

    Examples:
      | url       |
      | '/cities' |

  Scenario: REMOVE FAIL: DELETE to /cities/999 should return not found City
    Given I hit "DELETE" to endpoint <url>
    Then there is no "city" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No City entity with id 999 exists!"

    Examples:
      | url           | id    |
      | '/cities/999' | '999' |

  Scenario: REMOVE FAIL: DELETE to /citys/1 must not remove City with child registry
    Given I hit "DELETE" to endpoint <url>
    Then should return status code "409"
    And should return error title message "Conflict"
    And should return error status at body "409"
    And should return error detail message "Entity City with id 1 can not be removed because has child registry!"

    Examples:
      | url         |
      | '/cities/1' |