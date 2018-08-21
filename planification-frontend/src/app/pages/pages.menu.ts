export const PAGES_MENU = [
  {
    path: 'pages',
    children: [
      {
        path: 'dashboard',
        data: {
          menu: {
            title: 'general.menu.dashboard',
            icon: 'ion-android-home',
            selected: false,
            expanded: false,
            order: 0
          }
        }
      }
      ,
      {
        path: 'administration',
        data: {
          roles: ['ROLE_ADMIN'],
          menu: {
            title: 'general.menu.administration',
            icon: 'ion-gear-a',
            selected: false,
            expanded: false,
            order: 250,
          },
        },
        children: [
          {
            path: 'user-management',
            data: {
              roles: ['ROLE_ADMIN'],
              menu: {
                title: 'general.menu.userManagement',
              }
            }
          },
          {
            path: 'habilitation-management',
            data: {
              roles: ['ROLE_ADMIN'],
              menu: {
                title: 'general.menu.habilitationManagement',
              }
            }
          },
          {
            path: 'unit-management',
            data: {
              roles: ['ROLE_ADMIN'],
              menu: {
                title: 'general.menu.unitManagement',
              }
            }

          },
          {
            path: 'operator-management',
            data: {
              roles: ['ROLE_ADMIN'],
              menu: {
                title: 'general.menu.operatorManagement',
              }
            }

          },
          {
            path: 'article-management',
            data: {
              roles: ['ROLE_ADMIN'],
              menu: {
                title: 'general.menu.articleManagement',
              }
            }

          }
        ]

      }
      ,
      {
        path: 'gestion-fichiers',
        data: {
          menu: {
            title: 'general.menu.gestionFichiers',
            icon: 'ion-gear-a',
            selected: false,
            expanded: false,
            order: 0
          }
        },
        children: [
          {
            path: 'fichier-polyvalence',
            data: {
              roles: ['ROLE_ADMIN'],
              menu: {
                title: 'general.menu.fichierPolyvalence',
              }
            }
          },
          {
            path: 'cadence-client',
            data: {
              roles: ['ROLE_ADMIN'],
              menu: {
                title: 'general.menu.cadenceClient',
              }
            }
          }
        
        ]

      },
      
      {
        path: 'gestion-scenario',
        data: {
          menu: {
            title: 'general.menu.gestionScenario',
            icon: 'ion-gear-a',
            selected: false,
            expanded: false,
            order: 0
          }
        },
        children: [
          {
            path: 'consultation-scenario',
            data: {
              roles: ['ROLE_ADMIN'],
              menu: {
                title: 'general.menu.consultationScenario',
              }
            }
          },
        
        
        ]

      },
      
    ]

  }
];
