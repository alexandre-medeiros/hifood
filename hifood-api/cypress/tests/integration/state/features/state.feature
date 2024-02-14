Feature: State endpoint tests

  Scenario: GET ALL: GET to /states should return all States
    Given I hit "GET" to endpoint "/states"
    Then should return all "state"
    And should return status code "200"

  Scenario: GET ONE: GET to /states/1 should return State existent with id=1 in database
    Given I hit "GET" to endpoint <url>
    Then should return the "state" existent in database with same id <id>
    And should return status code "200"

    Examples:
      | url         | id  |
      | '/states/1' | '1' |

  Scenario: NOT FOUND: GET to /states/999 should return no State found
    Given I hit "GET" to endpoint <url>
    Then there is no "state" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No State entity with id 999 exists!"

    Examples:
      | url           | id    |
      | '/states/999' | '999' |

  Scenario: CREATE: POST to /states should create a new state
    Given I hit "POST" to "/states" with data:
      """
      {
        "name": "New State"
      }
      """
    Then the "state" is registred with success
    And should return status code "201"

  Scenario: CREATE FAIL: POST to /states fail because field name is too small
    Given I hit "POST" to "/states" with data:
      """
      {
        "name": "Ve"
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 20"


  Scenario: CREATE FAIL: POST to /states fail because field name is too large
    Given I hit "POST" to "/states" with data:
      """
      {
        "name": "Very much big great value"
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 20"

  Scenario: CREATE FAIL: POST to /states fail because field name should not be null
    Given I hit "POST" to "/states" with data:
      """
      {
        "name": null
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 20"

  Scenario: CREATE FAIL: POST to /states fail because an unrecognized field exist
    Given I hit "POST" to "/states" with data:
      """
      {
        "description": "description",
        "name": "Name of state create fail"
      }
      """
    Then should return status code "400"
    And should return error title message "Invalid Body"
    And should return error status at body "400"
    And should return error detail message "Unrecognized field 'description' "

  Scenario: UPDATE: PUT to /states/1 should update the State with id=1
    Given I hit "PUT" to "/states/1" with data:
      """
      {
        "name": "Minas Name Updated"
      }
      """
    Then the "state" with id "1" is updated with success
    And should return status code "200"

  Scenario: UPDATE FAIL: PUT to /states/999 with unexistent state id should return not found State
    Given I hit "PUT" to <url> with data:
      """
      {
        "name": "New Updated Expected"
      }
      """
    Then there is no "state" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No State entity with id 999 exists!"

    Examples:
      | url           | id    |
      | '/states/999' | '999' |

  Scenario: REMOVE: DELETE to /states/{id} should remove deleteable State in database
    Given I hit "POST" to "/states" with data:
      """
      {
        "name": "Deleteable State"
      }
      """
    Then the "state" is registred with success
    Given I hit DELETE to endpoint <url> with a deleteable id
    Then should remove the "state" existent in database with same id
    And should return status code "204"

    Examples:
      | url       |
      | '/states' |

  Scenario: REMOVE FAIL: DELETE to /states/999 should return not found State
    Given I hit "DELETE" to endpoint <url>
    Then there is no "state" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No State entity with id 999 exists!"

    Examples:
      | url           | id    |
      | '/states/999' | '999' |

  Scenario: REMOVE FAIL: DELETE to /states/1 should not remove State with child registry
    Given I hit "DELETE" to endpoint <url>
    Then should return status code "409"
    And should return error title message "Conflict"
    And should return error status at body "409"
    And should return error detail message "Entity State with id 1 can not be removed because has child registry!"

    Examples:
      | url         |
      | '/states/1' |