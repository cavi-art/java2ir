CAVI-ART Java2IR
================

This project aims to build a compiler from Java (supporting up to Java 8) 
to CLIR, the CAVI-ART Intermediate Representation.

This project is in incubation phase, and still far from complete.

Features will be added directly into the master branch.

Releases will be available directly at the [GitHub releases page][rel].


Documentation
=============

This project uses [spoon][spoon], an open-source library for transforming 
and analyzing Java code.

[spoon]: http://spoon.gforge.inria.fr


Future Usage
============

    java -jar java2ir.jar \
      -limit-package com.github.cavi_art \ 
      path/to/project/src/main/java \
      -o path/to/out


Dependencies
============

This project uses Maven to track dependencies.

We depend on some of our own Java-based tooling for CAVI-ART, available at 
https://github.com/cavi-art and also through jitpack.io as maven dependencies.


Contributing
============

This project encourages the [GitHub Flow][flow] for external
contributions. Please, send any improvements you may find via a GitHub
Pull Request. You can also send them via email (preferrably as a git
request-pull, but I can also accept git format-patch patches) and I
will merge them.


Acknowledgements
================

This work is partially supported by the Spanish MINECO project
CAVI-ART (TIN2013-44742-C4-3-R), Madrid regional project N-GREENS
Software-CM (S2013/ICE-2731) and UCM grant GR3/14-910502.

CAVI-ART stands for Computer Assisted ValIdation by Analysis,
tRansformation and Testing.



  [flow]: https://guides.github.com/introduction/flow/
  [rel]: https://github.com/cavi-art/java2ir/releases
