Feature: Kitchen endpoint tests

  Scenario: GET ALL: GET to /kitchens should return all Kitchens
    Given I hit "GET" to endpoint "/kitchens"
    Then should return all "kitchen"
    And should return status code "200"

  Scenario: GET ONE: GET to /kitchens/1 should return Kitchen existent with id=1 in database
    Given I hit "GET" to endpoint <url>
    Then should return the "kitchen" existent in database with same id <id>
    And should return status code "200"

    Examples:
      | url           | id  |
      | '/kitchens/1' | '1' |

  Scenario: NOT FOUND: GET to /kitchens/999 should return no Kitchen found
    Given I hit "GET" to endpoint <url>
    Then there is no "kitchen" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No Kitchen entity with id 999 exists!"

    Examples:
      | url             | id    |
      | '/kitchens/999' | '999' |

  Scenario: CREATE: POST to /kitchens should create a new kitchen
    Given I hit "POST" to "/kitchens" with data:
      """
      {
        "name": "New Kitchen"
      }
      """
    Then the "kitchen" is registred with success
    And should return status code "201"

  Scenario: CREATE FAIL: POST to /kitchens fail because field name is too small
    Given I hit "POST" to "/kitchens" with data:
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

  Scenario: CREATE FAIL: POST to /kitchens fail because field name is too large
    Given I hit "POST" to "/kitchens" with data:
      """
      {
        "name": "Very much big great gigant value"
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 20"

  Scenario: CREATE FAIL: POST to /kitchens fail because field name must not be null
    Given I hit "POST" to "/kitchens" with data:
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

  Scenario: CREATE FAIL: POST to /kitchens fail because field name must exist
    Given I hit "POST" to "/kitchens" with data:
      """
      {

      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field name must be present and must have size between 3 and 20"

  Scenario: CREATE FAIL: POST to /kitchens fail because an unrecognized field exist
    Given I hit "POST" to "/kitchens" with data:
      """
      {
        "description": "description",
        "name": "Name of kitchen create fail"
      }
      """
    Then should return status code "400"
    And should return error title message "Invalid Body"
    And should return error status at body "400"
    And should return error detail message "Unrecognized field 'description' "

  Scenario: UPDATE: PUT to /kitchens/1 must update the Kitchen with id=1
    Given I hit "PUT" to "/kitchens/1" with data:
      """
      {
        "name": "Uberlândia Name Updated"
      }
      """
    Then the "kitchen" with id "1" is updated with success
    And should return status code "200"

  Scenario: UPDATE FAIL: PUT to /kitchens/999 must return not found Kitchen
    Given I hit "PUT" to <url> with data:
      """
      {
        "name": "Name Unexistent Kitchen"
      }
      """
    Then there is no "kitchen" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No Kitchen entity with id 999 exists!"

    Examples:
      | url             | id    |
      | '/kitchens/999' | '999' |

  Scenario: REMOVE: DELETE to /kitchens/{id} should remove deleteable Kitchen in database
    Given I hit "POST" to "/kitchens" with data:
      """
      {
        "name": "Deleteable Kitchen"
      }
      """
    Then the "kitchen" is registred with success
    Given I hit DELETE to endpoint <url> with a deleteable id
    Then should remove the "kitchen" existent in database with same id
    And should return status code "204"

    Examples:
      | url         |
      | '/kitchens' |

  Scenario: REMOVE FAIL: DELETE to /kitchens/999 should return not found Kitchen
    Given I hit "DELETE" to endpoint <url>
    Then there is no "kitchen" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No Kitchen entity with id 999 exists!"

    Examples:
      | url             | id    |
      | '/kitchens/999' | '999' |

  Scenario: REMOVE FAIL: DELETE to /kitchens/1 must not remove Kitchen with child registry
    Given I hit "DELETE" to endpoint <url>
    Then should return status code "409"
    And should return error title message "Conflict"
    And should return error status at body "409"
    And should return error detail message "Entity Kitchen with id 1 can not be removed because has child registry!"

    Examples:
      | url           |
      | '/kitchens/1' |