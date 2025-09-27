const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Postulante = require('../models/postulante');

const postulanteController = new GenericController(Postulante);

router.get('/', postulanteController.getAll);
router.post('/', postulanteController.create);
router.get('/:id', postulanteController.getById);
router.put('/:id', postulanteController.update);
router.delete('/:id', postulanteController.delete);

module.exports = router;