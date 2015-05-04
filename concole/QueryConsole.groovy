rigenera = {
ctx.getBean('bootStrapService').utenteMaster = 'rossonet'
ctx.getBean('bootStrapService').macchinaMaster = 'lucifero.rossonet.net'
ctx.getBean('bootStrapService').portaMaster = 22
ctx.getBean('bootStrapService').idInterfacciaScelta = 'Bootstrap-Ar4k'
ctx.getBean('bootStrapService').idContestoScelto = 'Bootstrap-Ar4k'
ctx.getBean('bootStrapService').keyMaster = """-----BEGIN RSA PRIVATE KEY-----
MIIEpQIBAAKCAQEAvFrGfpYBex2WCKuuPGk6D9Zpjwvz60MMfXDza1SlweFGZTMq
0edGvrGv04PAXC2gPd5+zGaTbS19q4NkHb7fdFiA0AGhzv0LiV+2TwC8j9OcGeja
xWQiYi3MIcWrQAHP8h49wNP6sPunq5TogWRbnaVtZrLr2GdaVEaNbBSUwtpbc0pL
e6aetyIxJsUx/dhzR0nk5dPYVfr/53cbqJ+N0RtbUulWDUem1jKaJNWQ5tZZb3AB
37lLZvkAj1kVwBsA+dVl0+niCdXPX3TVme+JJ1p1f6i75FSPmCiEWaB0jbsz61OO
nLGJXH6t3On7GRfcFC5rOdQsQ1RUgklfEyIeNwIDAQABAoIBAEXvtrAd5qLeE21Y
wbMwpxxmUn+IwqLiHGsfW2LI5Vsd2FOPMiEKLNx1gYGZ8/zF/C+8w3wFwkvRjk32
1V+uFa5PhlS1pm0CzBvP7BNdhKYqvlVofoK4cYPe2WLY0INNqPaPgAJvMzAv4Mv2
2HXi25mnaP2vBZ8ukP2lzLEK20mVwzhUeydw1Cw2Npvy3AcpxmWNG119jOPe3CUE
dKZfKwo7gKaO9ZGBZW5cuHfu0+4y/5KzK4NlfQJ6k6jWKnuYOLcTnRqTDUn+qT+d
3JszV/VWzVbtDbl+cdAe/BZ1FjBGxGGLSnCR2LHgfZ4ePkzMlLdUvM+sXGWvckRj
GYQaUNECgYEA3R24fl2b2gZlsK6AYqVmHt+w3fl9qh/b1U3jTots7Ub5f3J4sfwm
KodNx8Xx7hX8iHtH4Q94Ra2Utz3qwEv9Vs1wfkNRw9TO+Uxe8sJouveA5YmZCdfh
rHKxydMq1JNYjC5Q0e+nj18tH2/5O/MuNc6PeXgFXV4fgeHB+S3oBRMCgYEA2hHo
B90BiR0cq16lo38vLEmZZWjt0MbMikW+3+pjBsgiCfnRqEqKTLIutRNMSwDAFghu
lzPZaiFAiX9yzTKb/R9d2qTaxpq8sojA4Ut12wkV0LSpyheZNZki2+Fc+suvuEB5
reLLY4K5WsPBS8A8UqMGhnz400rVbPCjBnbJes0CgYEAksg0/eXEHOJcRQJCBboI
ZXxW27PQIMwPUbTnrO9POt//pgPmbTiWWWsCThhv9n2v6yXzXEq5q3+EFnRKzqZj
IWk1QQwNTjOJxeJVsU8u2NaOQVGrPuaJT8G/ldvRNdYuVLg2PHabw8gJmzHowEw5
I8zgX2E2Jnnh7oVgD7rSp/MCgYEAm7J9QGADYKpwdcq16O49+Bj0dPteENpf37m8
dpMa8RSjeQvtLYNLC0eNIawNpN98Rj7u9vYGHUhwk7tzdB7WUPu8PwfFTFfesnoB
fNor45+w+7qCH9CJi0MzKFrX0Bjt9VfTiNuM7BfBz5RExpkSeM39Mri3z+R2Tbni
LBNT92ECgYEAltURrm4p6DbZghJXV1TWYydX5n2HLqgyTXKHXsdpj0Ui4Sfy4Kqd
egoB1/Zp/a0Fo5elcntsqd36ssyJ1XwYVDIiB8qdO43hbZ+8U/CKZ6htWE3uSrKw
mhsIc8K31IrWgutPgXfw84/vYzUgjrpUfqssYKbfN6g4wyumx2rLsaM=
-----END RSA PRIVATE KEY-----"""

ctx.getBean('bootStrapService').avvia()
}

// Rigenera l'ambiente.
//rigenera()

// Disabilita o abilita il bootstrap da web
//ctx.getBean('bootStrapService').inAvvio = true

// Simulazione battito di Quartz
ctx.getBean('interfacciaContestoService').battito()

// Rapporto
//println ctx.getBean('bootStrapService').toString()

// Stampa configurazione contesto
//println ctx.getBean('bootStrapService').contesto.esporta()

// Esempio metalinguaggio
//println ctx.metaClass.methods*.name.sort().unique()