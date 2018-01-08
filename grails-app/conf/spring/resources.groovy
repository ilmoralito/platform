import ni.edu.ucc.leon.UserPasswordEncoderListener

beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))

    dateUtil(ni.edu.ucc.leon.DateUtil) { bean ->
        bean.autowire = true
    }
}
