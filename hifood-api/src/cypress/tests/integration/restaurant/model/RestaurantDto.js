class RestaurantDto {
  constructor(id, name, deliveryFees, kitchen) {
    this.id = id;
    this.name = name;
    this.deliveryFees = deliveryFees;
    this.kitchen = kitchen;
  }

  getId() {
    return this.id;
  }

  getName() {
    return this.name;
  }

  getDeliveryFees() {
    return this.deliveryFees;
  }

  getKitchen() {
    return this.kitchen;
  }

  getDto() {
    return {
      id: this.id,
      name: this.name,
      deliveryFees: this.deliveryFees,
      kitchen: this.kitchen,
    };
  }

  toDto(object) {
    this.id = object.id;
    this.name = object.name;
    this.deliveryFees = object.deliveryFees;
    this.kitchen = object.kitchen.id;

    return this;
  }

  toListDto(list) {
    return list.map((restaurant) => {
      this.toDto(restaurant);
      return this.getDto();
    });
  }
}

const restaurantDto = new RestaurantDto();

module.exports = restaurantDto;
