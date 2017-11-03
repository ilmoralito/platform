package ni.edu.ucc.leon

import ni.edu.ucc.leon.Color

class CoordinationColorService {

    List<Color> listColorsByCoordination(final Coordination coordination) {
        CoordinationColor.findAllByCoordination(coordination)
    }
}
