const express = require('express');
const router = express.Router();
const GenericController = require('../controllers/generic.controller');
const Semestre = require('../models/semestre');

const semestreController = new GenericController(Semestre);

router.get('/', semestreController.getAll);
router.post('/', semestreController.create);
router.get('/:id', semestreController.getById);
router.put('/:id', semestreController.update);
router.delete('/:id', semestreController.delete);

module.exports = router;