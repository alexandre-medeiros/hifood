Feature: Restaurant endpoint tests

  Scenario: GET ALL: GET to /restaurants should return all Restaurants
    Given I hit "GET" to endpoint "/restaurants"
    Then should return all restaurants
    And should return status code "200"

  Scenario: GET ONE: GET to /restaurants/1 should return Restaurant existent with id=1 in database
    Given I hit "GET" to endpoint <url>
    Then should return the "restaurant" existent in database with same id <id>
    And should return status code "200"

    Examples:
      | url              | id  |
      | '/restaurants/1' | '1' |

  Scenario: NOT FOUND: GET to /restaurants/999 should return no Restaurant found
    Given I hit "GET" to endpoint <url>
    Then there is no "restaurant" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No Restaurant entity with id 999 exists!"

    Examples:
      | url                | id    |
      | '/restaurants/999' | '999' |

  Scenario: CREATE: POST to /restaurants should create a new restaurant
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "New Restaurant",
        "deliveryFees": 10,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then the "restaurant" is registred with success
    And should return status code "201"

  Scenario: CREATE FAIL: POST to /restaurants fail because kitchen does not exist
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "New Restaurant",
        "deliveryFees": 10,
        "kitchen": {
          "id": 999
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "No Kitchen entity with id 999 exists!"

  Scenario: CREATE FAIL: POST to /restaurants fail because city does not exist
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "New Restaurant",
        "deliveryFees": 10,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 999
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "No City entity with id 999 exists!"

  Scenario: CREATE FAIL: POST to /restaurants fail because field name is too small
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "Ne",
        "deliveryFees": 10,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field restaurant name must be present and must have size between 5 and 20"

  Scenario: CREATE FAIL: POST to /restaurants fail because field name is too large
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "Very much big great gigant value Very much big great gigant value Very much big great",
        "deliveryFees": 10,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field restaurant name must be present and must have size between 5 and 20"

  Scenario: CREATE FAIL: POST to /restaurants fail because field name must not be null
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": null,
        "deliveryFees": 10,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field restaurant name must be present and must have size between 5 and 20"

  Scenario: CREATE FAIL: POST to /restaurants fail because field name must exist
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "deliveryFees": 10,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "name" to field "Field restaurant name must be present and must have size between 5 and 20"

  Scenario: CREATE FAIL: POST to /restaurants fail because field deliveryFees must exist
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "New Restaurant",
        "deliveryFees": null,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "deliveryFees" to field "Field delivery fee  must not be null"

  Scenario: CREATE FAIL: POST to /restaurants fail because field deliveryFees must be positive
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "New Restaurant",
        "deliveryFees": -1,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "deliveryFees" to field "Field delivery fee must be greater than zero"

  Scenario: CREATE FAIL: POST to /restaurants fail because field kitchen must exist
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "New Restaurant",
        "deliveryFees": 10,
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "kitchen" to field "Field kitchen  must not be null"

  Scenario: CREATE FAIL: POST to /restaurants fail because field kitchen id must not be null
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "New Restaurant",
        "deliveryFees": 10,
        "kitchen": {
          "id": null
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "Invalid Params"
    And should return error message "kitchen.id" to field "Field kitchen.id  must not be null"

  Scenario: CREATE FAIL: POST to /restaurants fail because kitchen id has invalid type
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "New Restaurant",
        "deliveryFees": 10,
        "kitchen": {
          "id": "AA"
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Invalid Body"
    And should return error status at body "400"
    And should return error detail message "The property 'kitchen.id' received the value 'AA' that is invalid. Send a compatible value with 'Long'"

  Scenario: CREATE FAIL: POST to /restaurants fail because an unrecognized field exist
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "description": "description",
        "name": "New Restaurant",
        "deliveryFees": 10,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then should return status code "400"
    And should return error title message "Invalid Body"
    And should return error status at body "400"
    And should return error detail message "Unrecognized field 'description' "

  Scenario: UPDATE: PUT to /restaurants/1 must update the Restaurant with id=1
    Given I hit "PUT" to "/restaurants/1" with data:
      """
      {
        "name": "Update Name Restaurant",
        "deliveryFees": 19,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then the "restaurant" with id "1" is updated with success
    And should return status code "200"

  Scenario: UPDATE FAIL: PUT to /restaurants/999 must return not found Restaurant
    Given I hit "PUT" to <url> with data:
      """
      {
        "name": "Update Name Restaurant",
        "deliveryFees": 19,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then there is no "restaurant" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No Restaurant entity with id 999 exists!"

    Examples:
      | url                | id    |
      | '/restaurants/999' | '999' |

  Scenario: UPDATE FAIL: PUT to /restaurants/1 with unexistent kitchen id must return not found Kitchen
    Given there is a "restaurant" with id <id>
    When I hit "PUT" to <url> with data:
      """
      {
        "name": "Update Name Restaurant",
        "deliveryFees": 19,
        "kitchen": {
          "id": 999
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then the "restaurant" with id <id> was not updated
    And should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "No Kitchen entity with id 999 exists!"

    Examples:
      | url              | id  |
      | '/restaurants/1' | '1' |

  Scenario: UPDATE FAIL: PUT to /restaurants/1 with unexistent city id must return not found City
    Given there is a "restaurant" with id <id>
    When I hit "PUT" to <url> with data:
      """
      {
        "name": "Update Name Restaurant",
        "deliveryFees": 19,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 999
          }
        }
      }
      """
    Then the "restaurant" with id <id> was not updated
    And should return status code "400"
    And should return error title message "Bad Request"
    And should return error status at body "400"
    And should return error detail message "No City entity with id 999 exists!"

    Examples:
      | url              | id  |
      | '/restaurants/1' | '1' |


  Scenario: REMOVE: DELETE to /restaurants/{id} should remove deleteable Restaurant in database
    Given I hit "POST" to "/restaurants" with data:
      """
      {
        "name": "Deletable Restaurant",
        "deliveryFees": 19,
        "kitchen": {
          "id": 1
        },
        "address": {
          "zipCode": "0045689",
          "street": "Streat name",
          "number": "1345",
          "city": {
            "id": 1
          }
        }
      }
      """
    Then the "restaurant" is registred with success
    Given I hit DELETE to endpoint <url> with a deleteable id
    Then should remove the "restaurant" existent in database with same id
    And should return status code "204"

    Examples:
      | url            |
      | '/restaurants' |

  Scenario: REMOVE FAIL: DELETE to /restaurants/999 should return not found Restaurant
    Given I hit "DELETE" to endpoint <url>
    Then there is no "restaurant" in database with same id <id>
    And should return status code "404"
    And should return error title message "Not Found"
    And should return error status at body "404"
    And should return error detail message "No Restaurant entity with id 999 exists!"

    Examples:
      | url                | id    |
      | '/restaurants/999' | '999' |

  Scenario: REMOVE FAIL: DELETE to /restaurants/1 must not remove Restaurant with child registry
    Given I hit "DELETE" to endpoint <url>
    Then should return status code "409"
    And should return error title message "Conflict"
    And should return error status at body "409"
    And should return error detail message "Entity Restaurant with id 1 can not be removed because has child registry!"

    Examples:
      | url              |
      | '/restaurants/1' |