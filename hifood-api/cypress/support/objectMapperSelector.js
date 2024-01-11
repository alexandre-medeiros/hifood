const restaurantModel = require("../tests/integration/restaurant/model/RestaurantModel");
const restaurantDto = require("../tests/integration/restaurant/model/RestaurantDto");

const mapper = {
  restaurant: {
    model: restaurantModel,
    dto: restaurantDto,
  },
};

module.exports = { mapper };
