const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Facultad = require('../models/facultad');

const facultadController = new GenericController(Facultad);

router.get('/', facultadController.getAll);
router.post('/', facultadController.create);
router.get('/:id', facultadController.getById);
router.put('/:id', facultadController.update);
router.delete('/:id', facultadController.delete);

module.exports = router;