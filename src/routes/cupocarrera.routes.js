const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const CupoCarrera = require('../models/cupocarrera');

const cupoCarreraController = new GenericController(CupoCarrera);

router.get('/', cupoCarreraController.getAll);
router.post('/', cupoCarreraController.create);
router.get('/:id', cupoCarreraController.getById);
router.put('/:id', cupoCarreraController.update);
router.delete('/:id', cupoCarreraController.delete);

module.exports = router;