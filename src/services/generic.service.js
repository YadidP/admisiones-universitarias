class GenericService {
  constructor(model) {
    this.model = model;
  }

  async getAll() {
    return await this.model.findAll();
  }

  async getById(id) {
    return await this.model.findByPk(id);
  }

  async create(data) {
    return await this.model.create(data);
  }

  async update(id, data) {
    const [updated] = await this.model.update(data, {
      where: { id: id }
    });
    if (updated) {
      return await this.model.findByPk(id);
    }
    return null;
  }

  async delete(id) {
    return await this.model.destroy({
      where: { id: id }
    });
  }
}

module.exports = GenericService;