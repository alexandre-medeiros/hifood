class RestaurantModel {
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

  getRestaurantModel() {
    return {
      id: this.id,
      name: this.name,
      deliveryFees: this.deliveryFees,
      kitchen: this.kitchen,
    };
  }

  toDomainModel(databaseObj) {
    const json = JSON.stringify(databaseObj);
    const object = JSON.parse(json);
    this.id = object.id;
    this.name = object.name;
    this.deliveryFees = Number(object.delivery_fees);
    this.kitchen = object.kitchen_id;

    return this;
  }

  toListDomainModel(list) {
    return list.map((restaurant) => {
      this.toDomainModel(restaurant);
      return this.getRestaurantModel();
    });
  }
}

const restaurantModel = new RestaurantModel();

module.exports = restaurantModel;
