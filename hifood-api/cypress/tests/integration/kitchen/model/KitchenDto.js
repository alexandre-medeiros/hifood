class KitchenDto {
  constructor(id, name) {
    this.id = id;
    this.name = name;
  }

  getId() {
    return this.id;
  }

  getName() {
    return this.name;
  }

  getDto() {
    return {
      id: this.id,
      name: this.name,
    };
  }

  toDto(object) {
    this.id = object.id;
    this.name = object.name;

    return this;
  }

  toListDto(list) {
    return list.map((domain) => {
      this.toDto(domain);
      return this.getDto();
    });
  }
}

const kitchenDto = new KitchenDto();

module.exports = kitchenDto;
