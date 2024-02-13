class CityDto {
  constructor(id, name, stateId) {
    this.id = id;
    this.name = name;
    this.stateId = stateId;
  }

  getId() {
    return this.id;
  }

  getName() {
    return this.name;
  }

  getStateId() {
    return this.stateId;
  }

  getDto() {
    return {
      id: this.id,
      name: this.name,
      stateId: this.stateId,
    };
  }

  toDto(object) {
    this.id = object.id;
    this.name = object.name;
    this.stateId = object.state.id;

    return this;
  }

  toListDto(list) {
    return list.map((domain) => {
      this.toDto(domain);
      return this.getDto();
    });
  }
}

const cityDto = new CityDto();

module.exports = cityDto;
