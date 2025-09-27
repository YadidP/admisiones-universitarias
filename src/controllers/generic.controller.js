const GenericService = require('../services/generic.service');

class GenericController {
  constructor(model) {
    this.service = new GenericService(model);
  }

  getAll = async (req, res) => {
    try {
      const items = await this.service.getAll();
      res.status(200).json(items);
    } catch (error) {
      res.status(500).json({ message: `Error retrieving items`, error: error.message });
    }
  };

  getById = async (req, res) => {
    try {
      const item = await this.service.getById(req.params.id);
      if (item) {
        res.status(200).json(item);
      } else {
        res.status(404).json({ message: 'Item not found' });
      }
    } catch (error) {
      res.status(500).json({ message: `Error retrieving item`, error: error.message });
    }
  };

  create = async (req, res) => {
    try {
      const newItem = await this.service.create(req.body);
      res.status(201).json(newItem);
    } catch (error) {
      res.status(500).json({ message: `Error creating item`, error: error.message });
    }
  };

  update = async (req, res) => {
    try {
      const updatedItem = await this.service.update(req.params.id, req.body);
      if (updatedItem) {
        res.status(200).json(updatedItem);
      } else {
        res.status(404).json({ message: 'Item not found' });
      }
    } catch (error) {
      res.status(500).json({ message: `Error updating item`, error: error.message });
    }
  };

  delete = async (req, res) => {
    try {
      const deleted = await this.service.delete(req.params.id);
      if (deleted) {
        res.status(204).send();
      } else {
        res.status(404).json({ message: 'Item not found' });
      }
    } catch (error) {
      res.status(500).json({ message: `Error deleting item`, error: error.message });
    }
  };
}

module.exports = GenericController;