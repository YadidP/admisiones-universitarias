const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Aula = require('../models/aula');

const aulaController = new GenericController(Aula);

router.get('/', aulaController.getAll);
router.post('/', aulaController.create);
router.get('/:id', aulaController.getById);
router.put('/:id', aulaController.update);
router.delete('/:id', aulaController.delete);

module.exports = router;