const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const CursoPreuniversitario = require('../models/cursopreuniversitario');

const cursoPreuniversitarioController = new GenericController(CursoPreuniversitario);

router.get('/', cursoPreuniversitarioController.getAll);
router.post('/', cursoPreuniversitarioController.create);
router.get('/:id', cursoPreuniversitarioController.getById);
router.put('/:id', cursoPreuniversitarioController.update);
router.delete('/:id', cursoPreuniversitarioController.delete);

module.exports = router;