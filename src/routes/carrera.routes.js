const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Carrera = require('../models/carrera');

const carreraController = new GenericController(Carrera);

router.get('/', carreraController.getAll);
router.post('/', carreraController.create);
router.get('/:id', carreraController.getById);
router.put('/:id', carreraController.update);
router.delete('/:id', carreraController.delete);

module.exports = router;