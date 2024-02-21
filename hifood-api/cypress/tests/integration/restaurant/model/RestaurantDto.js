class RestaurantDto {
  constructor(id, name, deliveryFees, kitchen, zipCode, street, number, city) {
    this.id = id;
    this.name = name;
    this.deliveryFees = deliveryFees;
    this.kitchen = kitchen;
    this.zipCode = zipCode;
    this.street = street;
    this.number = number;
    this.city = city;
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

  getZipCode() {
    return this.zipCode;
  }

  getStreet() {
    return this.street;
  }

  getNumber() {
    return this.number;
  }

  getCity() {
    return this.city;
  }

  getDto() {
    return {
      id: this.id,
      name: this.name,
      deliveryFees: this.deliveryFees,
      kitchen: this.kitchen,
      zipCode: this.zipCode,
      street: this.street,
      number: this.number,
      city: this.city,
    };
  }

  toDto(object) {
    this.id = object.id;
    this.name = object.name;
    this.deliveryFees = object.deliveryFees;
    this.kitchen = object.kitchen.id;
    this.zipCode = object.address != null ? object.address.zipCode : null;
    this.street = object.address != null ? object.address.street : null;
    this.number = object.address != null ? object.address.number : null;
    this.city = object.address != null ? object.address.city.id : null;

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
