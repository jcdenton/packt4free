[ ![Download](https://api.bintray.com/packages/madhead/madhead/packt4free/images/download.svg) ](https://bintray.com/madhead/madhead/packt4free/_latestVersion)

# Usage

    export PACKT4FREE_SMTP_SERVER=smtp.gmail.com; export PACKT4FREE_SMTP_PORT=587; export PACKT4FREE_SMTP_USERNAME=<user>@gmail.com; export PACKT4FREE_SMTP_PASSWORD=<password>; export PACKT4FREE_FROM=<user>@gmail.com; export PACKT4FREE_TO=<user>@gmail.com; /path/to/packt-free-ebook/bin/packt-free-ebook

It's better to run this with `cron`.

If you use 2FA (and you must use it!), you'll need to configure "Application Password" [here](https://security.google.com/settings/security/apppasswords).
