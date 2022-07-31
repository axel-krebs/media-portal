import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';

// maybe using 'await' here
i18n
  // detect user language
  // learn more: https://github.com/i18next/i18next-browser-languageDetector
  .use(LanguageDetector)
  // pass the i18n instance to react-i18next.
  .use(initReactI18next)
  .init({
    debug: true,
    fallbackLng: 'en',
    interpolation: {
        escapeValue: false, // not needed for react as it escapes by default
    },
    resources: {
        en: {
          translation: {
            "footer_legal": "© 1997 - 2022 AFRTN Television LLC. A Division of The McGwire Ishikawa Organization LLC. All Rights Reseved. Trademark & Copyright Notice.",
            "link_legal_notice": "Legal Notice",
            "link_jobs_career": "Jobs & Careers",
            "link_impressum": "Imprint"
          }
        },
        de: {
            translation: {
                "footer_legal": "© 1997 - 2022 AFRTN Television LLC. Ein Unternehmen der McGwire Ishikawa Organization LLC. Alle Rechte vorbehalten. Trademark & Copyright Notice.",
                "link_legal_notice": "Rechtliche Hinweise",
                "link_jobs_career": "Jobs & Karriere",
                "link_impressum": "Impressum"
            }
        }
    }
  });

export default i18n;