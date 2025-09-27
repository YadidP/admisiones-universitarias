const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const ResultadoExamen = require('../models/resultadoexamen');

const resultadoExamenController = new GenericController(ResultadoExamen);

router.get('/', resultadoExamenController.getAll);
router.post('/', resultadoExamenController.create);
router.get('/:id', resultadoExamenController.getById);
router.put('/:id', resultadoExamenController.update);
router.delete('/:id', resultadoExamenController.delete);

module.exports = router;