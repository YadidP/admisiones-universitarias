const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Examen = require('../models/examen');

const examenController = new GenericController(Examen);

router.get('/', examenController.getAll);
router.post('/', examenController.create);
router.get('/:id', examenController.getById);
router.put('/:id', examenController.update);
router.delete('/:id', examenController.delete);

module.exports = router;